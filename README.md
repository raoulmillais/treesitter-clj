# Reproduction of problem wrapping treesitter library

This is just the sample code from the treesitter website translated into
clojure using coffi.  The sample C code builds and runs without error (see
`main.c`)

The included treesitter `.so` is the latest version of tree-sitter (on an arch
linux system - so latest upstream) (same for the `api.h` header). The
included `libtree-sitter-clojure.so` is a build from the git HEAD of the
project (via AUR)

```sh
clang                                 \
  -L $PWD/resources/                  \
  -ltree-sitter                       \
  -ltree-sitter-clojure               \
  -I $PWD/resources/api.h             \
  main.c                              \
  -o test-clojure-tree-sitter
LD_LIBRARY_PATH=$PWD/resources test-clojure-tree-sitter
```

The clojure code segfaults when calling `ts_node_string` on the root node. I
have included the logfile when it ran on my machine: `hs_err_pid770616.log`

See the `src/treesitter_clj/core/clj` file.  Evaluate it all and then each of
the forms in the comment block one by one.

The included `coffi.jar` is a build of the `develop` branch of coffi.

## Coredump stacktracce

```
      Message: Process 635109 (java) of user 1000 dumped core.

                Stack trace of thread 635167:
                #0  0x00007237452cb3f4 n/a (libc.so.6 + 0x963f4)
                #1  0x0000723745272120 raise (libc.so.6 + 0x3d120)
                #2  0x00007237452594c3 abort (libc.so.6 + 0x244c3)
                #3  0x0000723743c1074e n/a (libjvm.so + 0x1074e)
                #4  0x0000723744a08214 n/a (libjvm.so + 0xe08214)
                #5  0x0000723744b2eac0 n/a (libjvm.so + 0xf2eac0)
                #6  0x00007237448a3b03 JVM_handle_linux_signal (libjvm.so + 0xca3b03)
                #7  0x00007237452721d0 n/a (libc.so.6 + 0x3d1d0)
                #8  0x0000723742753e74 ts_language_symbol_count (libtree-sitter.so.0.23 + 0x5e74)
                #9  0x0000723742754010 ts_language_symbol_name (libtree-sitter.so.0.23 + 0x6010)
                #10 0x000072372bf5cb3e n/a (n/a + 0x0)
                #11 0x000072372bc672d6 n/a (n/a + 0x0)
                #12 0x000072372bc672d6 n/a (n/a + 0x0)
                #13 0x000072372bc672d6 n/a (n/a + 0x0)
                #14 0x000072372bc6737a n/a (n/a + 0x0)
                #15 0x000072372bc6737a n/a (n/a + 0x0)
                #16 0x000072372517b84c n/a (n/a + 0x0)
                #17 0x000072372bc67880 n/a (n/a + 0x0)
                #18 0x000072372bc67880 n/a (n/a + 0x0)
                #19 0x000072372bc6737a n/a (n/a + 0x0)
                #20 0x0000723724de407c n/a (n/a + 0x0)
                #21 0x000072372bc6737a n/a (n/a + 0x0)
                #22 0x000072372bc6737a n/a (n/a + 0x0)
                #23 0x000072372bc67880 n/a (n/a + 0x0)
                #24 0x000072372c8475cc n/a (n/a + 0x0)
                ELF object binary architecture: AMD x86-64
```
