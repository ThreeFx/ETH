{-# LANGUAGE ScopedTypeVariables #-}

module ResCalc where

import Data.Set (Set(..))
import qualified Data.Set as Set

import Data.List (intercalate)

import Debug.Trace

import Prelude hiding (concatMap, not)

data Var a = Var a | Not (Var a)

instance Show a => Show (Var a) where
  show (Var a) = show a
  show (Not a) = "¬(" ++ show a ++ ")"

instance (Eq a) => Eq (Var a) where
  a == b = normalise a `eqVar` normalise b
    where
      eqVar (Var x) (Var y) = x == y
      eqVar (Not a) (Not b) = a `eqVar` b
      eqVar _       _       = False

instance (Ord a) => Ord (Var a) where
  a <= b = normalise a `cmpVar` normalise b
    where
      cmpVar (Var a) (Var b) = a <= b
      cmpVar (Not a) (Not b) = a `cmpVar` b
      cmpVar _       _       = False

toList :: Set (Clause a) -> [[Var a]]
toList = map Set.toList . Set.toList

showClauses :: Show a => Set (Clause a) -> String
showClauses list = intercalate "^" $ map (("(" ++) . (++")") . intercalate "v") $ map (map show) $ toList list

stripNot :: Var a -> Var a
stripNot (Not a) = stripNot a
stripNot x       = x

normalise :: Var a -> Var a
normalise (Not (Not a)) = normalise a
normalise a             = a

--isUnsat :: Ord a => Var a -> Set (Clause a) -> Bool
--isUnsat var clauses = helper var clauses (2^(length clauses))

type Clause a = Set (Var a)

--resolve :: Ord a => Var a -> Set (Clause a) -> Set (Clause a)
resolve var c = c `Set.union` resolve' var c

mkSet :: Ord a => [a] -> Set a
mkSet = Set.fromList

normSet :: Ord a => Clause a -> Clause a
normSet = Set.map normalise

--resolve' :: (Show a, Ord a) => Var a -> Set (Clause a) -> Set (Clause a)
resolve' var clauses = concatMap (\has -> Set.map (\hasn't -> Set.delete var $ Set.delete (not var) $ (has `Set.union` hasn't)) hasNot) hasVar
  where
    usefulClauses = Set.filter (Set.member var . Set.map stripNot) $ clauses
    (hasVar, hasNot) = Set.partition (Set.member var) $ usefulClauses

tracee :: Show a => a -> a
tracee = \x -> trace ("arg: " ++ show x ++ "\n") x

concatMap :: Ord b => (a -> Set b) -> Set a -> Set b
concatMap f = Set.foldr' Set.union Set.empty . Set.map f

not = Not
a = Var 'a'
b = Var 'b'
c = Var 'c'
d = Var 'd'
e = Var 'e'
z = Var 'z'
testCase = mkSet $ map mkSet [[not a, c], [not z, c], [not b, a], [not e, z], [not a, not c, d, e], [not a, not d, z], [a], [b], [not z]]
