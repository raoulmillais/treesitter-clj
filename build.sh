clang                                 \
  -L $PWD/resources/                  \
  -ltree-sitter                       \
  -ltree-sitter-clojure               \
  -I $PWD/api.h                       \
  main.c                              \
  -o test-clojure-tree-sitter

