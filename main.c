#include <string.h>
#include <stdio.h>
#include <tree_sitter/api.h>

const TSLanguage *tree_sitter_clojure(void);

int main() {
  TSParser *parser = ts_parser_new();

  ts_parser_set_language(parser, tree_sitter_clojure());

  const char *source_code = "(+ 1 2)";
  TSTree *tree = ts_parser_parse_string(
    parser,
    NULL,
    source_code,
    strlen(source_code)
  );

  TSNode root_node = ts_tree_root_node(tree);

  char *string = ts_node_string(root_node);
  printf("%s\n", string);

  free(string);
  ts_tree_delete(tree);
  ts_parser_delete(parser);
  return 0;
}
