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

(ns pokerepl.test-card
  (:use [clojure.test]
        [pokerepl.core]))

(deftest syntactic-sugar-ctor
  (is (= (Card :ace :spades)
         (Card "AS")
         (Card \A \S))))

(deftest pretty-printing
  (let [exp-inp {"TC" [:ten :clubs] 
                 "A?" [:ace :xyz]
                 "?D" [\F \D]
                 "??" [nil]}]
    (doseq [expected (keys exp-inp)]
      (is (= expected
             (str (apply Card (get exp-inp expected))))))))
