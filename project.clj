(defproject treesitter-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.suskalo/coffi "0.6.409"]]
  :resource-paths ["resources/coffi.jar"]
  :jvm-opts ^:replace ["--enable-preview" "--enable-native-access=ALL-UNNAMED"]
  :repl-options {:init-ns treesitter-clj.core})
