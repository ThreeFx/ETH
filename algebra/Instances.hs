{-# LANGUAGE GeneralizedNewtypeDeriving, FlexibleInstances, UndecidableInstances, DeriveFunctor #-}

module Instances where

import Algebra
import Prelude hiding (Monoid, mappend, mempty)
import Control.Applicative
import qualified Prelude as P

newtype Sum a     = Sum     { getSum     :: a } deriving (Show, Eq, Ord, Functor, Num)
newtype Product a = Product { getProduct :: a } deriving (Show, Eq, Ord, Functor, Num, Fractional)

instance Applicative Sum where
  pure = Sum
  (Sum fa) <*> (Sum a) = Sum $ fa a

instance Applicative Product where
  pure = Product
  (Product fa) <*> (Product a) = Product $ fa a

instance Semiring [a] where
  sappend = (++)

instance Monoid [a] where
  mempty = []


instance Num a => Semiring (Sum a) where
  sappend = (+)

instance Num a => Monoid (Sum a) where
  mempty = Sum 0

instance Num a => Group (Sum a) where
  ginverse x = (-x)


instance Num a => Semiring (Product a) where
  sappend = (*)

instance Num a => Monoid (Product a) where
  mempty = Product 1

instance Fractional a => Group (Product a) where
  ginverse = (1 /)

instance Num a => Ring a where
  rappendg a b = getSum $ Sum a <> Sum b
  rappendm a b = getProduct $ Product a <> Product b
  remptyg      = 0
  remptym      = 1
  rinverse x   = (-x)
