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

(use 'com.muckandbrass.interpolate)
(import (java.util Date))

;; TODO Unwart this:
;; No single method: find_player of interface: pokerepl.casino.ICasino found for function: find-player of protocol: ICasino
;;  [Thrown class java.lang.IllegalArgumentException]
(declare find-player)

(deftype Casino [name players]
  ICasino

   (player-enter [name]
     (let [player (find-player @players name)]
       (if player
         (println (<< "~{player} is already in the casino"))
         (dosync 
          (alter players conj 
                 (with-meta (Player name 0) {:entry (Date.)}))))))
   
   (player-exit  [name]
     (let [player (find-player @players name)]
       (if player
         (dosync (alter players disj player))
         (println (<< "~{name} is not playing")))))

   (players      [] @players)
  )

(defn find-player [players name]
  (first (filter #(= (:name %) name) players)))

