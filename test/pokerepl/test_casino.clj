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

(ns pokerepl.test-casino
  (:use [clojure.test]
        [pokerepl.core]))

(deftest entry-tests
  (let [casino (Casino "Royale" (ref #{}))]
    (is (= (:name casino) "Royale"))
    (is (= 0 (count @(:players casino))))
    (player-enter casino "Bond")
    (is (= 1 (count @(:players casino))))
    ))

(deftest roundtrip-tests
  (let [casino (Casino "Royale" (ref #{}))]
    (is (= 0 (count @(:players casino))))
    (player-enter casino "Bond")
    (is (= 1 (count @(:players casino))))
    (player-exit casino "Moo")
    (is (= 1 (count @(:players casino))))
    (player-exit casino "Bond")
    (is (= 0 (count @(:players casino))))
  ))

;; (def casino (Casino "Royale" (ref #{})))

;; (player-enter casino "Bond")
;; (player-enter casino "Bono")
;; (players casino)
;; (player-exit casino "Moo")
;; (players casino)
;; (player-exit casino "Bono")
;; (players casino)

;; (with-meta "foo" {:bar 'baz})
