;; Copyright (C) 2009, 2010 Seth Schroeder <goof@foognostic.net>
;; 
;; This file is part of pokerepl.
;; 
;; pokerepl is free software: you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.
;; 
;; pokerepl is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.
;; 
;; You should have received a copy of the GNU General Public License
;; along with pokerepl.  If not, see <http://www.gnu.org/licenses/>.
;; 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; -*- mode: clojure -*-
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(in-ns 'pokerepl.core)

(def suits [:clubs :hearts :diamonds :spades])
(def suit-map
     (zipmap suits [\C \H \D \S]))

(def values [:two :three :four :five :six :seven :eight
             :nine :ten :jack :queen :king :ace])
(def value-map
     (zipmap values [\2 \3 \4 \5 \6 \7 \8 \9 \T \J \Q \K \A]))

;; destructuring not supported for "constructor" arguments?
;; yes
;; java.lang.IllegalArgumentException: No matching method found: intern
;;  [Thrown class java.lang.RuntimeException]

(deftype Card [#^clojure.lang.Keyword value
               #^clojure.lang.Keyword suit]
  ICard
  (toString []
            (str (get value-map value "?")
                 (get suit-map suit "?"))))

(def old-Card Card)

(defn Card
  ([two-char-str]
     (apply Card (map (partial nth (seq two-char-str)) [0 1])))

  ([value suit]
     (let [value-sym (if (= clojure.lang.Keyword (class value))
                       value
                       (get (zipmap (vals value-map) (keys value-map)) value))
           suit-sym (if (= clojure.lang.Keyword (class suit))
                      suit
                      (get (zipmap (vals suit-map) (keys suit-map)) suit))]
       (old-Card value-sym suit-sym))))

