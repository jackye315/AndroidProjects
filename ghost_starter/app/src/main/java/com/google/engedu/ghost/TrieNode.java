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

package com.google.engedu.ghost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class TrieNode {
    private HashMap<String, TrieNode> children;
    private boolean isWord;
    Random rand = new Random();

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public void add(String s) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < s.length(); i++) {
            HashMap<String, TrieNode> child = root.children;
            String prefix = "" + s.charAt(i);
            if (child.containsKey(prefix)) {
                root = child.get(prefix);
            } else {
                TrieNode temp = new TrieNode();
                child.put(prefix, temp);
                root = temp;
            }
        }
        //TrieNode end = new TrieNode();
        //HashMap<String, TrieNode> child = root.children;
        //child.put(".", end);
        //root = end;
        root.isWord = true;
    }

    public boolean isWord(String s) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < s.length(); i++) {
            HashMap<String, TrieNode> child = root.children;
            String prefix = "" + s.charAt(i);
            if (child.containsKey(prefix)) {
                root = child.get(prefix);
            } else {
                break;
            }
        }
        return root.isWord;
    }

    public String getAnyWordStartingWith(String s) {
        TrieNode root = new TrieNode();
        HashMap<String, TrieNode> child = root.children;
        String newword = s;
        System.out.println(child.size());
        for (int i = 0; i < s.length(); i++) {
            child = root.children;
            String prefix = "" + s.charAt(i);
            if (child.containsKey(prefix)) {
                root = child.get(prefix);
            } else {
                System.out.println("Hiie");
                return null;
            }
        }
        while (root != null && child.size() != 0) {
            int seed = rand.nextInt(child.size());
            ArrayList<String> possible_words = new ArrayList<>(child.keySet());
            String nextchar = possible_words.get(seed);
            newword = newword + nextchar;
            System.out.println(nextchar);
            root = child.get(nextchar);
            child = root.children;
        }

        return newword;
    }

    public String getGoodWordStartingWith(String s) {

        return null;
    }
}
