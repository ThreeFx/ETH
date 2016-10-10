{-# LANGUAGE GADTs #-}
{-# LANGUAGE StandaloneDeriving #-}
{-# LANGUAGE RankNTypes #-}
module Mengenlehre where

import Data.Foldable
import Data.Function
import Data.Monoid
import Data.Traversable

import Prelude hiding (filter)
import qualified Prelude as P

data Set a where
  Empty :: Set a
  Elem :: a -> Set a -> Set a
  Set :: Set a -> Set a -> Set a

deriving instance Show a => Show (Set a)
deriving instance Eq a => Eq (Set a)

prettyPrint :: Show a => Set a -> String
prettyPrint x = "{" ++ prettyPrint' x ++ "}"
  where
    prettyPrint' Empty = ""
    prettyPrint' (Elem x set) = show x ++ showList set
    prettyPrint' (Set s set)  = prettyPrint s ++ showList set
    showList Empty = ""
    showList (Elem x set) = "," ++ show x ++ showList set
    showList (Set s set)  = "," ++ prettyPrint s ++ showList set

instance Functor Set where
  fmap f Empty = Empty
  fmap f (Elem x set) = Elem (f x) (fmap f set)
  fmap f (Set s set) = Set (fmap f s) (fmap f set)

instance Applicative Set where
  pure x = Elem x Empty
  Empty         <*> x = Empty
  x             <*> Empty = Empty
  Elem fx  fxs  <*> x = fmap fx x `mappend`(fxs <*> x)
  Set  fxs fxss <*> x = Set (fxs <*> x) (fxss <*> x)

instance Monad Set where
  return = pure
  -- (>>=) :: Set a -> (a -> Set b) -> Set b
  Empty      >>= f = Empty
  Elem x set >>= f = f x `mappend` (set >>= f)
  Set  s set >>= f = Set (s >>= f) $ set >>= f

instance Foldable Set where
  foldMap _ Empty = mempty
  foldMap f (Elem x set) = f x `mappend` foldMap f set
  foldMap f (Set s set) = foldMap f s `mappend` foldMap f set

instance Traversable Set where
  -- traverse :: Applicative f => (a -> f b) -> Set a -> f (Set b)
  traverse f Empty = pure Empty
  traverse f (Elem x set) = Elem <$> f x <*> traverse f set
  traverse f (Set s set) = Set <$> traverse f s <*> traverse f set

instance Monoid (Set a) where
  mempty = Empty
  mappend Empty ys = ys
  mappend (Elem x set) ys = Elem x (set `mappend` ys)
  mappend (Set s set)  ys = Set s (set `mappend` ys)

setMap :: Monoid m => (a -> m) -> (Set a -> m) -> Set a -> m
setMap fe fs Empty = mempty
setMap fe fs (Elem x set) = fe x `mappend` setMap fe fs set
setMap fe fs (Set s set) = fs s `mappend` setMap fe fs set

-- this must be equal to the foldmap instance
foldMap' :: Monoid m => (a -> m) -> Set a -> m
foldMap' f = setMap f (fix $ setMap f)

forallSetMap :: Monoid m => (forall a. a -> m) -> Set a -> m
forallSetMap f = setMap f f

magnitude :: Set a -> Int
magnitude = getSum . forallSetMap (const $ Sum 1)

setFilter :: (a -> Bool) -> (Set a -> Bool) -> Set a -> Set a
setFilter fe fs Empty = Empty
setFilter fe fs (Elem x set)
  | fe x = Elem x $ setFilter fe fs set
  | otherwise = setFilter fe fs set
setFilter fe fs (Set s set)
  | fs s = Set s $ setFilter fe fs set
  | otherwise = setFilter fe fs set

setEq :: Eq a => Set a -> Set a -> Bool
setEq = (==) `on` norm

norm :: Eq a => Set a -> Set a
norm Empty = Empty
norm (Elem x set) = Elem x $ norm $ setFilter (/=x) (const True) set
norm (Set s set)  = Set (norm s) $ norm $ setFilter (const True) (/=s) set

set :: Set Int
-- set = {4,5,{{5},5,6,{5},{5},7},5,5}
set = Elem 4 (Elem 5 (Set (Set (Elem 5 Empty) (Elem 5 (Elem 6 (Set (Elem 5 Empty) (Set (Elem 5 Empty) (Elem 7 Empty)))))) (Elem 5 $ Elem 5 Empty)))

a :: Set Int
a = Elem 1 (Set (Elem 2 (Elem 3 Empty)) (Elem 4 Empty))

b :: Set Int
b = Elem 2 $ (Set (Elem 3 $ Elem 4 Empty) Empty)

c :: Set Int
c = Set (Elem 2 $ Elem 3 Empty) Empty
