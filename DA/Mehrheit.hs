{-# LANGUAGE FlexibleInstances #-}
import Data.Monoid
import Control.Monad
import Control.Applicative hiding (Const)

f :: Eq a => [a] -> Maybe a
f [] = Nothing
f l@(x:xs) = test l $ g (Just x) 1 xs

g :: Eq a => Maybe a -> Int -> [a] -> Maybe a
g x n [] = x
g Nothing n (y:ys) = g (Just y) 1 ys
g (Just x) n (y:ys)
  | x == y = g (Just x) (n+1) ys
  | n - 1 == 0 = g Nothing 0 ys
  | otherwise = g (Just x) (n-1) ys

test :: Eq a => [a] -> Maybe a -> Maybe a
test l Nothing = Nothing
test l (Just x) = let len = length $ filter (==x) l in
                    if len > (length l `div` 2)
                      then Just x
                      else Nothing

data Const a b = Const a

instance Functor (Const a) where
  fmap f (Const a) = Const a

instance Monoid m => Applicative (Const m) where
  pure b = Const mempty
  (Const a) <*> (Const b) = Const $ a `mappend` b

getConst (Const a) = a
