/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.*;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    ArrayList<String> wordList = new ArrayList<String>();
    HashSet<String> wordSet = new HashSet<String>();
    HashMap<String, ArrayList<String>> lettersToWords = new HashMap<String, ArrayList<String>>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            String sortword = sortLetters(word);
            wordSet.add(word);
            if (lettersToWords.containsKey(sortword)) {
                lettersToWords.get(sortword).add(word);
            } else {
                ArrayList<String> value = new ArrayList<String>();
                value.add(word);
                lettersToWords.put(sortword, value);
            }

        }
    }

    public String sortLetters(String input) {
        char [] temp = input.toCharArray();
        Arrays.sort(temp);
        String output = new String(temp);
        return output;
    }

    public boolean isGoodWord(String word, String base) {
        boolean bool = false;
        for (int i = 0; i < word.length() - base.length(); i++) {
            if (word.charAt(i) == base.charAt(0)) {
                for (int j = 1; j < base.length(); j++) {
                    if (word.charAt(i + j) == base.charAt(j)) {
                        bool = true;
                    } else {
                        bool = false;
                        j = j + base.length();
                    }
                }
            }
        }
        return bool && wordSet.contains(word);

    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();

        String sorttarget = sortLetters(targetWord);
        for (String word : wordList) {
            String sortword = sortLetters(word);
            if (sortword.length() == sorttarget.length() && sortword.equals(sorttarget)) {
                result.add(word);
            }
        }

        return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 97; i < 123; i++) {
            String newword = word + (char) i;
            String sortnewword = sortLetters(newword);
            if (isGoodWord(newword, word) && lettersToWords.containsKey(sortnewword)) {
                result.add(newword);
            }
        }


        return result;
    }

    public String pickGoodStarterWord() {
        return "badge";
    }
}
