{-# LANGUAGE GADTs #-}
{-# LANGUAGE StandaloneDeriving #-}
{-# LANGUAGE RankNTypes #-}
module Mengenlehre where

import Data.Foldable
import Data.Function
import Data.Monoid

data Set a where
  Empty :: Set a
  Elem :: a -> Set a -> Set a
  Set :: Set a -> Set a -> Set a

deriving instance Show a => Show (Set a)

instance Functor Set where
  fmap f Empty = Empty
  fmap f (Elem x set) = Elem (f x) (fmap f set)
  fmap f (Set s set) = Set (fmap f s) (fmap f set)

instance Foldable Set where
  foldMap _ Empty = mempty
  foldMap f (Elem x set) = f x `mappend` foldMap f set
  foldMap f (Set s set) = foldMap f s `mappend` foldMap f set

setMap :: Monoid m => (a -> m) -> (Set a -> m) -> Set a -> m
setMap fe fs Empty = mempty
setMap fe fs (Elem x set) = fe x `mappend` setMap fe fs set
setMap fe fs (Set s set) = fs s `mappend` setMap fe fs set

-- this must be equal to the foldmap instance
foldMap' :: Monoid m => (a -> m) -> Set a -> m
foldMap' f = setMap f (fix $ setMap f)

forallSetMap :: Monoid m => (forall a. a -> m) -> Set a -> m
forallSetMap f = setMap f f

norm :: Set a -> Set a
norm Empty = Empty
norm (Elem x set) = Empty

set = Elem 4 (Set (Elem 5 (Elem 6 (Set (Elem 5 Empty) Empty))) Empty)
