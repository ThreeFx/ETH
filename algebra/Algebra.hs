module Algebra where

import Prelude hiding (Monoid, mappend, mempty)
import qualified Prelude as P

class Semiring s where
  -- ^ sappend must be associative:
  -- a `sappend` (b `sappend` c) == (a `sappend` b) `sappend` c
  sappend :: s -> s -> s

(<>) :: Semiring s => s -> s -> s
(<>) = sappend

class Semiring m => Monoid m where
  mempty :: m

mappend :: Monoid m => m -> m -> m
mappend = sappend


class Monoid g => Group g where
  -- ^ ginverse a denotes the inverse of b, e.g. the
  -- b such that a `gappend` b = gempty = b `gappend` a
  ginverse :: g -> g

gappend :: Group g => g -> g -> g
gappend = mappend

gempty :: Group g => g
gempty = mempty


class Ring r where
  rappendg :: r -> r -> r
  rappendm :: r -> r -> r
  remptyg  :: r
  remptym  :: r
  rinverse :: r -> r
