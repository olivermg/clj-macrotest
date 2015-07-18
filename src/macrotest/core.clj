(ns macrotest.core
  (:gen-class))

(defmacro dfn
  [[& bindings] & methods]
  `(let [~@bindings]
     (fn [op# & args#]
       (apply
        (case op#
          ~@(reduce (fn [res [mname [& margs] & mbody]]
                      (concat res
                              [mname `(fn [~@margs] ~@mbody)]))
                    []
                    methods))
        args#))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
