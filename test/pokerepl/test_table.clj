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

(ns pokerepl.test-table
  (:use [clojure.test]
        [pokerepl.core]))

(deftest test-buy-in
  (let [casino (Casino "Royale" (ref #{}))
        player-starting-chips 123
        table-buy-in 100
        table (Table "High Rollers" table-buy-in casino (ref {}))
        player (Player "Bond" (atom player-starting-chips))]

    (buy-in table player)

    (is (= table-buy-in)
        (chips table player))

    (cash-out table player)

    (is (= player-starting-chips @(:chips player)))

    ))

(deftest test-betting
  ;; dup code will be refactored
  (let [casino (Casino "Royale" (ref #{}))
        player-starting-chips 123
        table-buy-in 100
        table (Table "High Rollers" table-buy-in casino (ref {}))
        player (Player "Bond" (atom player-starting-chips))
        hand (Hand table (atom 0))]
    
    (buy-in table player)

    (is (= 0 @(:pot hand)))

    (bet hand player 50)

    (is (= 50 @(:pot hand)))

    (is (= (- table-buy-in 50)
           (chips table player)))
  ))