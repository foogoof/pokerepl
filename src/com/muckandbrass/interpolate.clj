;; This code is not part of pokerepl. It is included here as an independent dependency, because
;; - no official distribution of this code exists beyond its original blog post
;; - unofficial permission was granted via IRC
;; - it's super cool!
;;
;; This code will be removed when the string interpolation code is packaged and distributed 
;; officially. Many thanks to Chas Emerick for creating this neat code!
;;
;; Originally posted to:
;;  http://muckandbrass.com/web/display/~cemerick/2009/12/04/String+Interpolation+in+Clojure
;;
;; Changes:
;;  changed namespace to com.muckandbrass.interpolate
;;

(ns com.muckandbrass.interpolate
  (:gen-class)
  (:use [clojure.contrib.duck-streams :only (slurp*)]))

(defn- silent-read
  [s]
  (try
    (let [r (-> s java.io.StringReader. java.io.PushbackReader.)]
      [(read r) (slurp* r)])
    (catch Exception e))) ; this indicates an invalid form -- s is just string data

(defn- interpolate
  ([s atom?]
    (lazy-seq
      (if-let [[form rest] (silent-read (subs s (if atom? 2 1)))]
        (cons form (interpolate (if atom? (subs rest 1) rest)))
        (cons (subs s 0 2) (interpolate (subs s 2))))))
  ([#^String s]
    (let [start (max (.indexOf s "~{") (.indexOf s "~("))]
      (if (== start -1)
        [s]
        (lazy-seq (cons
                    (subs s 0 start)
                    (interpolate (subs s start) (= \{ (.charAt s (inc start))))))))))

(defmacro <<
  [string]
  `(str ~@(interpolate string)))

