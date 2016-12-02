{-# LANGUAGE GADTs #-}
{-# LANGUAGE DataKinds, TypeFamilies, TypeOperators #-}
{-# LANGUAGE UndecidableInstances #-}
{-# LANGUAGE StandaloneDeriving #-}
{-# LANGUAGE InstanceSigs #-}

module Vector (Vector(..), (:+), (:*), (:-)) where

data Nat = Z | S Nat deriving (Show, Eq)

infixl 6 :+
infixl 7 :*
infixr 5 :-
infixl 6 :--

type family (n :: Nat) :+ (m :: Nat) :: Nat
type instance Z :+ m = m
type instance (S n) :+ m = n :+ S m

type family (n :: Nat) :* (m :: Nat) :: Nat
type instance Z :* m = Z
type instance (S n) :* m = n :* m :+ m

type family (n :: Nat) :-- (m :: Nat) :: Nat
type instance n :-- Z = n
type instance (S n) :-- (S m) = n :-- m

data Vector a n where
  Nil :: Vector a Z
  (:-) :: a -> Vector a n -> Vector a (S n)

append :: Vector a n -> Vector a m -> Vector a (n :+ m)
append (x :- xs) ys = x :- append xs ys
append Nil ys = ys

--instance Functor (Vector n) where
--  fmap f Nil = Nil
--  --fmap :: (n ~ m) => (a -> b) -> Vector n a -> Vector m a
--  fmap f (x :- xs) = f x :- xs

--instance Applicative (Vector n) where
--  pure x = x :- Nil
--  (<*>) :: Vector n (a -> b) -> Vector m a -> Vector (n :* m) b
--  fx <*> [] = []
--  fx <*> (x:-xs) = fmap fx x `append` fx <*> xs

deriving instance Show a => Show (Vector a n)
deriving instance Eq a => Eq (Vector a n)
