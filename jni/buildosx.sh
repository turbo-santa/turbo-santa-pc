g++ -shared -o libsanta_jni.dylib -I /Library/Java/JavaVirtualMachines/jdk1.8.0.jdk/Contents/Home/include/ -I /Library/Java/JavaVirtualMachines/jdk1.8.0.jdk/Contents/Home/include/darwin -fPIC EmulatorHandler.cc -L./osx -lncurses -lback_end -lpthread -lglog -framework IOKit -framework CoreFoundation
