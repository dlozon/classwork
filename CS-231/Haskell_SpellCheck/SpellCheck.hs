-- Dylan Lozon
-- Haskell Project 2 "SpellCheck"
-- 5/28/2021

import Data.List
import System.IO
import System.Environment
import Data.Char

-- Returns a lowercase version of a string
strToLower :: String -> String
strToLower [] = []
strToLower (x:xs) = toLower(x) : strToLower(xs)

-- Returns True if a string is entirely
-- made of alphabetic characters
strIsAlpha :: String -> Bool
strIsAlpha [] = True
strIsAlpha (x:xs) = isLetter(x) && strIsAlpha(xs)

-- Uses insertion sorting to alphabetize
-- a list of strings
-- CI stands for Case-Insensitive
sortCI :: [String] -> [String]
sortCI [] = []
sortCI (x:xs) = ins x (sortCI xs)

ins :: String -> [String] -> [String]
ins x [] = [x]
ins x (y:ys)
   | strToLower(x) <= strToLower(y) = x : (y:ys)
   | otherwise = y : ins x ys

-- Removes any strings with non-alphabetic
-- characters from a list
removeNonAlpha :: [String] -> [String]
removeNonAlpha [] = []
removeNonAlpha (x:xs)
   | strIsAlpha x = x : removeNonAlpha xs
   | otherwise = removeNonAlpha xs

-- Removes duplicate strings from a list
-- current implementation only works on
-- lists in alphabetical order
-- CI stands for Case-Insensitive
uniqCI :: [String] -> [String]
uniqCI [] = []
uniqCI (x:xs)
   | xs == [] = [x]
   | strToLower(x) == strToLower(head xs) = uniqCI xs
   | otherwise = x : uniqCI xs

-- Searches the dictionary for a list of words
-- returns a list of strings that say whether 
-- each index is or is not a word
compareToDict :: [String] -> [String] -> [String]
compareToDict [] [] = []
compareToDict [] (y:ys) = []
compareToDict (x:xs) [] = 
   "                          is NOT a word." : compareToDict xs []
compareToDict (x:xs) (y:ys)
   | strToLower(x) == strToLower(y) = 
      " is a word." : compareToDict xs ys
   | strToLower(x) < strToLower(y) = 
      "                       is NOT a word." : compareToDict xs ys
   | otherwise = compareToDict (x:xs) ys

-- Combines the strings in a list of tuples
joinStrings :: [(String,String)] -> [String]
joinStrings [] = []
joinStrings (x:xs) = (fst x ++ snd x) : joinStrings xs

-- Handles getting the input and calling
-- functions to find the result
main = do
      -- Get the three required args and
      -- open files in read or write mode
      [inFile, dictionary, outFile] <- getArgs
      inFileHandle <- openFile inFile ReadMode
      dictHandle <- openFile dictionary ReadMode
      outFileHandle <- openFile outFile WriteMode
      
      -- Process the contents of the input file
      -- into a list of words in alphabetical
      -- order, list contains no duplicate words
      input <- hGetContents inFileHandle
      let organizedInput = uniqCI(sortCI(removeNonAlpha(words(input))))
      
      -- Get the contents of the dictionary as a list
      dictionaryContents <- hGetContents dictHandle
      let dictionaryList = words(dictionaryContents)
      
      -- Make a list containing the results of the
      -- search and format the list for printing
      let resultData = compareToDict organizedInput dictionaryList
      let resultTuples = zip organizedInput resultData
      let resultFormatted = joinStrings resultTuples
      
      -- Prints the formatted output to the output file
      hPutStr outFileHandle ( unlines resultFormatted )
      -- Close the files
      hClose inFileHandle
      hClose dictHandle
      hClose outFileHandle
