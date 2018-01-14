(ns passphrase.core
  (:gen-class))

(defn entropy-per-word
  "Calculate entropy per word from wordlist size"
  [n]
  (int (/ (Math/log n) (Math/log 2))))

(defn list-from-file
  "Take a file name and return wordlist, sanitized and shuffled"
  [file]
  (->> file
   (slurp)
   (clojure.string/split-lines)
   (filter #(re-matches #"^[a-z]*$" %))
   (distinct)
   (shuffle)))

(defn -main
  "Generate passphrase from wordlist"
  [& args]
  (let [length 8
        filename "/usr/share/dict/words"
        words (list-from-file filename)]
    (println (take length words))
    (println "Minumum of"
             (entropy-per-word (count words))
             "bits of entropy per word")))

