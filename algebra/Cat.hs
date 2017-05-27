{-# LANGUAGE PolyKinds, TypeOperators, KindSignatures,
             ConstraintKinds, MultiParamTypeClasses,
             TypeSynonymInstances, FlexibleInstances,
             TypeFamilies, UndecidableInstances, ScopedTypeVariables,
             AllowAmbiguousTypes, Rank2Types
#-}


import Prelude hiding ((.), id, fmap, Functor, Monad, bind, return, (>>=), (=<<))
import qualified Prelude as P

import GHC.Exts (Constraint)

type (a ~> b) c = c a b

class Category (c :: k -> k -> *) where
  id  :: (a ~> a) c
  (.) :: (y ~> z) c -> (x ~> y) c -> (x ~> z) c

type Hask = (->)

instance Category Hask where
  id  = P.id
  (.) = (P..)

class (Category c, Category d) => Functor c d f where
  fmap :: (a ~> b) c -> (f a ~> f b) d

newtype Identity a = Identity a

instance Functor Hask Hask Identity where
  fmap f (Identity x) = Identity $ f x

--data List a = Cons a (List a) | Nil deriving (Eq, Show)

instance Functor Hask Hask [] where
  fmap f [] = []
  fmap f (x:xs) = f x : fmap f xs

instance Functor Hask Hask Maybe where
  fmap f Nothing = Nothing
  fmap f (Just x) = Just $ f x

type Endofunctor c f = Functor c c f
newtype FCompose g f a = FCompose { getCompose :: g (f a) }
newtype Hom (c :: * -> Constraint) a b = Hom (a -> b)

instance (Functor a b f, Functor b c g, c ~ Hom k) => Functor a c (FCompose g f) where
  fmap f = Hom FCompose . fmapg (fmapf f) . Hom getCompose
    where
      fmapf = fmap :: a x y -> b (f x) (f y)
      fmapg = fmap :: b s t -> c (g s) (g t)

type NaturalTransformation c f g = forall a. (f a ~> g a) c
type NatHask f g = NaturalTransformation Hask f g

-- category Fun of functors
newtype Fun f g a b = FNat (f a -> g b)
-- category of endofunctors
type End f = Fun f f

instance Category (End f) where
  id  = FNat id
  (.) (FNat f) (FNat g) = FNat (f P.. g)


class Endofunctor c f => Monad c f where
  eta :: (a ~> f a) c
  mu  :: (f (f a) ~> f a) c

instance Monad Hask [] where
  eta x = [x]
  mu    = concat

bind :: Monad c m => (a ~> m b) c -> (m a ~> m b) c
bind f = mu . fmap f

newtype Kleisli c m a b = Kleisli c (a) (m b)

instance (Monad c m) => Category (Kleisli c m) where
  id  = Kleisli eta
-- (.) :: Monad m c => (y ~> m z) c -> (x ~> m y) c -> (x ~> m z) c
  (Kleisli yz) . (Kleisli xy) = Kleisli $ mu . fmap yz . xy

(<=<) = (.)
(>=>) = flip (.)


