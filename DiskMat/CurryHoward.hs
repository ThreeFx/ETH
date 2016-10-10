{-# LANGUAGE TypeOperators #-}
{-# LANGUAGE RankNTypes #-}
{-# LANGUAGE ExistentialQuantification #-}
{-# LANGUAGE LambdaCase #-}

module CurryHoward where

import Data.Void
import Prelude hiding (Either(..))

infix 5 /\
infix 5 \/

infix 4 <->

data (/\) a b = Conj a b deriving Show

data (\/) a b = Left a | Right b deriving Show

type (<->) a b = (a -> b) /\ (b -> a)

type Not a = a -> Void

assume :: p
assume = assume

-- assume the excluded middle
-- not provable in constructive logic
excluded_middle :: p \/ Not p
excluded_middle = assume

double_negation :: Not (Not p) -> p
-- ((p -> Void) -> Void) -> p
double_negation = \nnp -> case excluded_middle of
                            Left p -> p
                            Right np -> absurd $ nnp np

modus_ponens :: (p -> q) /\ p -> q
modus_ponens (Conj pq p) = pq p

modus_tollens :: (p -> q) /\ Not q -> Not p
modus_tollens (Conj pq not_q) = \p -> not_q $ pq p

{-impl_is_or :: (p -> q) <-> Not p \/ q
impl_is_or = Conj left_to_right right_to_left
  where
    -- (p -> q) -> Not p \/ q
    left_to_right = \pq -> undefined
    -- Not p \/ q -> (p -> q)
    right_to_left = \case -- np_o_q of
                      Left np -> absurd . np
                      Right q -> const q
-}

transposition :: (p -> q) <-> (Not q -> Not p)
transposition = Conj left_to_right right_to_left
  where
    -- (p -> q) -> (not q -> not p)
    left_to_right = \pq not_q -> modus_tollens pq not_q
    -- (not q -> not p) -> (p -> q)
    -- (not q -> not p) -> p -> q
    -- ((q -> Void) -> (p -> Void)) -> (p -> q)
    -- (Not ((q -> Void) -> (p -> Void)) \/ p) -> (p -> q)
    -- (((q -> Void) -> (p -> Void)) -> Void) \/ p
    right_to_left = \nq_np -> case excluded_middle of
                      Left nq_np -> absurd nq_np -- TODO: more case?
                      Right p -> pq p

