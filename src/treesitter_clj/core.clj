(ns treesitter-clj.core
  (:require [coffi.mem :as mem :refer [defalias]]
            [coffi.ffi :as ffi :refer [defcfn]])
  (:import (java.lang.foreign MemorySegment)))

(ffi/load-library "resources/libtree-sitter.so")
(ffi/load-library "resources/libtree-sitter-clojure.so")

(defcfn tree_sitter_clojure
  tree_sitter_clojure [] ::mem/pointer)

(defcfn ts_language_symbol_count
  ts_language_symbol_count [::mem/pointer] ::mem/int)

(defcfn ts_parser_new
  ts_parser_new [] ::mem/pointer)

(defcfn ts_parser_set_language
  ts_parser_set_language [::mem/pointer ::mem/pointer] ::mem/void)

(defcfn ts_parser_parse_string
  ts_parser_parse_string [::mem/pointer ::mem/pointer ::mem/c-string ::mem/int] ::mem/pointer)

(defcfn ts_tree_root_node
  ts_tree_root_node [::mem/pointer] ::mem/pointer)

(defcfn ts_node_string
  ts_node_string [::mem/pointer] ::mem/c-string)

(comment
  (def parser (ts_parser_new))
  (def lang (tree_sitter_clojure))
  (ts_parser_set_language parser lang)
  (def source_code "(+ 1 2)")
  (def tree 
    (ts_parser_parse_string 
      parser 
      MemorySegment/NULL
      source_code 
      (count source_code)))
  (def root_node (ts_tree_root_node tree))
  (prn (ts_language_symbol_count lang)) ;; This function call is the last named entry in the coredump stacktrace before aborting - but printing it here works
  (prn (ts_node_string root_node)))

