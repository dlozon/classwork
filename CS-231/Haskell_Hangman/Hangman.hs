-- Dylan Lozon
-- Haskell Final "Traditional Hangman"
-- 6/18/21

import Data.List
import System.IO
import System.Environment
import Data.Char
import Data.String
import Control.Monad
import System.Random

-- Formats the current gamestate 
-- for output to the console
display :: String -> String
display word = addSpaces 
   [if( isPresent ['a'..'z'] c )
   then '_' 
   else c | c <- word]

-- Puts whitespace inbetween each
-- char in the given string
addSpaces :: String -> String
addSpaces [] = []
addSpaces [x] = [x]
addSpaces (x:xs) = x : ' ' : addSpaces xs

-- Returns an uppercase version of the input
strToUpper :: String -> String
strToUpper [] = []
strToUpper (x:xs) = toUpper(x) : strToUpper(xs)

-- Returns a lowercase version of the input
strToLower :: String -> String
strToLower [] = []
strToLower (x:xs) = toLower(x) : strToLower(xs)

-- Uses insertion sorting to alphabetize
-- a list of strings
-- CI stands for Case-Insensitive
sortCI :: [Char] -> [Char]
sortCI [] = []
sortCI (x:xs) = ins x (sortCI xs)

ins :: Char -> [Char] -> [Char]
ins x [] = [x]
ins x (y:ys)
   | toUpper(x) <= toUpper(y) = x : (y:ys)
   | otherwise = y : ins x ys

-- Returns true if the given character is 
-- present within the given string
isPresent :: String -> Char -> Bool
isPresent [] y = False
isPresent (x:xs) y
   | x == y = True
   | otherwise = isPresent xs y
   
-- Returns false if any of a list of chars
-- is present within the given string
areNotPresent :: String -> [Char] -> Bool
areNotPresent (x:xs) [] = True
areNotPresent (x:xs) (y:ys)
   | isPresent (x:xs) y = False
   | otherwise = areNotPresent (x:xs) ys

-- Appends a char to the front of a string
append :: String -> Char -> String
append [] y = [y]
append (x:xs) y = (y:x:xs)

-- Processes the player's guess
makeGuess :: String -> String -> Char -> Int -> IO ()
makeGuess word prevGuesses letter guesses
   | isPresent word letter = play 
     [if( letter == c ) 
     then( toUpper letter )
     else c | c <- word] ( append prevGuesses letter ) guesses
   | otherwise = play word (append prevGuesses letter) (guesses - 1)

-- A 'loop' that plays the game until
-- either the player has guessed the
-- word or has run out of guesses
play :: String -> String -> Int -> IO ()
play word prevGuesses guesses
   | word == strToUpper word = do
      putStrLn $ display word
      putStrLn "Nice job, you win!"
   | guesses == 0 = do
      putStrLn $ display word
      putStrLn "Too bad, better luck next time!"
      putStrLn $ "The word was " ++ strToUpper word ++ "."
   | otherwise = do
      putStrLn $ "\nYou have " ++ show guesses ++ " guesses remaining."
      putStrLn $ "Guessed Letters: " ++
         addSpaces(sortCI(strToUpper prevGuesses))
      putStrLn $ display word
      guess <- getLine
      case guess of
         c:_ -> if( isLetter(head guess) && areNotPresent guess prevGuesses) 
                then makeGuess word prevGuesses (toLower $ head guess) guesses
                else putStrLn "Input was not a letter or was already guessed."
                   >> play word prevGuesses guesses
         _ -> putStrLn "No input detected." >> play word prevGuesses guesses

-- Driver function for the code
-- Has 2 args: the mode in which the game will be played
-- (standard or evil) and the file to get words from
main :: IO ()
main = do
   [inFile] <- getArgs
   inFileHandle <- openFile inFile ReadMode
   
   fileContents <- hGetContents inFileHandle
   
   case fileContents of
      c:_ -> do 
         putStrLn "Let's play hangman! Guess a letter to start."
         let secretWord = head(words(head(lines(fileContents))))
         play( strToLower secretWord ) "" 6
      _ -> putStrLn "Err: file provided in arg2 is empty."             
              
   hClose inFileHandle
