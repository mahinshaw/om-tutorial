(ns om-tutorial.E-State-Reads-and-Parsing-Exercises
  (:require-macros
    [cljs.test :refer [is]]
    )
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [om-tutorial.queries.exercise-util :as u]
            [devcards.core :as dc :refer-macros [defcard defcard-doc]]
            [cljs.reader :as r]
            [om.next.impl.parser :as p]))



(defcard-doc
  "# State and Parsing Exercises

  In this section you learned about turning queries into results and turning abstract calls into
  mutates of the application state. In the following exercises we'll continue
  working on our components from the UI Exercises section. The solutions to the query exercises are
  in the top of this file.

  Now it is time to write read functions that can turn queries into result data.

  TODO:

  Write the exercises using deftest cards, so that as they solve the problems the tests pass.

  - Build up from basics
  - recursive examples?
  - Look at the AST for bits

  ")

(defcard sample-ast
         "This card can show you the AST for arbitrary queries."
         (fn [state _]
           (let [{:keys [v]} @state]
             (dom/div nil
                      (dom/input #js {:type     "text"
                                      :value    v
                                      :onChange (fn [evt] (swap! state assoc :v (.. evt -target -value)))})
                      (dom/button #js {:onClick #(try
                                                  (swap! state assoc :error "" :ast (p/expr->ast (r/read-string v)))
                                                  (catch js/Error e (swap! state assoc :error e))
                                                  )} "Evaluate AST")

                      ))
           )
         {:ast []}
         {:inspect-data true}
         )


