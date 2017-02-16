{-# LANGUAGE NoImplicitPrelude #-}
{-# LANGUAGE DeriveFunctor #-}
{-# LANGUAGE GeneralizedNewtypeDeriving #-}

module Algebra where

import qualified Prelude as P
import Prelude (Eq, Ord, Show, Num, Functor, Applicative, Monad)

-- mempty is left and right neutral element
-- `mappend` is associative:
-- a <> (b <> c) == (a <> b) <> c
class Monoid m where
  mempty :: m
  mappend :: m -> m -> m

(<>) :: Monoid m => m -> m -> m
(<>) = mappend

class Monoid g => Group g where
  ginverse :: g -> g

gempty :: Group g => g
gempty = mempty

gappend :: Group g => g -> g -> g
gappend = mappend

{- Instances -}

newtype Sum a = Sum { getSum :: a }
  deriving (Show, Eq, Ord, Num, Functor, Applicative)
newtype Product a = Product { getProduct :: a }
  deriving (Show, Eq, Ord, Num, Functor, Applicative)

instance Num a => Monoid (Sum a) where
  mempty = Sum 0
  mappend a b = (+) <$> a <*> b

instance Num a => Monoid (Product a) where
  mempty = Product 1
  mappend a b = (*) <$> a <*> b
