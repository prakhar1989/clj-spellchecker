> Happy Holidays :santa:

Peter Norvig's [spellchecker](http://norvig.com/spell-correct.html) in Clojure!

This festive season, I decided to give myself the gift of learning a lisp - Clojure! This small *project* is a quick experiment in migrating Python code to Clojure and getting a taste of the joy that is so fondly associated with the language. 

Since I didnt want to commit a big file in Git, the project contains a small one.
Grab [big.txt](http://norvig.com/big.txt) first if you want the spellchecker to give better results!


To run

```
$ git clone https://github.com/prakhar1989/spellchecker
$ cd spellchecker

#optional step
$mv ~/mybigfile.txt resources/words-file.txt


$ lein repl
(correct "speling" nwords)
> spelling
```

To test
```
$ lein test
```
