require File.expand_path(File.dirname(__FILE__) + "/init")
require "ruby_slim"
require 'jcode'
$KCODE="UTF8"

rubySlim = RubySlim.new
rubySlim.run(ARGV[0].to_i)