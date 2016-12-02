{-# LANGUAGE GADTs #-}

data AVL a = Nil
           | L (AVL a) a (AVL a)
           | B (AVL a) a (AVL a)
           | R (AVL a) a (AVL a)
           deriving (Eq, Ord, Show)

singleton :: Ord a => a -> AVL a
singleton a = B Nil a Nil

insert :: Ord a => a -> AVL a -> AVL a
insert a (L lt n Nil)


restoreAVL :: AVL a -> AVL a
restoreAVL Nil = Nil
restoreAVL 
