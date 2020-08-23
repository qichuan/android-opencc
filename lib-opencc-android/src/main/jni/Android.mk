LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE    := Marisa
LOCAL_C_INCLUDES += src/main/jni/OpenCC/deps/marisa-0.2.5/include/
LOCAL_C_INCLUDES += src/main/jni/OpenCC/deps/marisa-0.2.5/lib/

LOCAL_SRC_FILES := \
OpenCC/deps/marisa-0.2.5/lib/marisa/trie.cc \
OpenCC/deps/marisa-0.2.5/lib/marisa/agent.cc \
OpenCC/deps/marisa-0.2.5/lib/marisa/grimoire/io/reader.cc \
OpenCC/deps/marisa-0.2.5/lib/marisa/grimoire/io/writer.cc \
OpenCC/deps/marisa-0.2.5/lib/marisa/grimoire/io/mapper.cc \
OpenCC/deps/marisa-0.2.5/lib/marisa/grimoire/trie/louds-trie.cc \
OpenCC/deps/marisa-0.2.5/lib/marisa/grimoire/trie/tail.cc \
OpenCC/deps/marisa-0.2.5/lib/marisa/grimoire/vector/bit-vector.cc \
OpenCC/deps/marisa-0.2.5/lib/marisa/keyset.cc

include $(BUILD_STATIC_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := OpenCC
LOCAL_C_INCLUDES += src/main/jni/OpenCC/deps/darts-clone/
LOCAL_C_INCLUDES += src/main/jni/OpenCC/deps/marisa-0.2.5/include/
LOCAL_C_INCLUDES += src/main/jni/OpenCC/deps/rapidjson-1.1.0/
LOCAL_STATIC_LIBRARIES := Marisa

LOCAL_SRC_FILES := \
OpenCC/src/Config.cpp \
OpenCC/src/Conversion.cpp\
OpenCC/src/ConversionChain.cpp \
OpenCC/src/Converter.cpp \
OpenCC/src/Dict.cpp \
OpenCC/src/DictEntry.cpp \
OpenCC/src/DictGroup.cpp \
OpenCC/src/Lexicon.cpp \
OpenCC/src/MarisaDict.cpp \
OpenCC/src/MaxMatchSegmentation.cpp \
OpenCC/src/SerializedValues.cpp \
OpenCC/src/SimpleConverter.cpp \
OpenCC/src/TextDict.cpp \
OpenCC/src/UTF8Util.cpp

include $(BUILD_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_PRELINK_MODULE := false

LOCAL_MODULE 	:= ChineseConverter
LOCAL_C_INCLUDES += src/main/jni/OpenCC/src/
LOCAL_STATIC_LIBRARIES := OpenCC
LOCAL_LDLIBS  += -llog -landroid

LOCAL_SRC_FILES := chineseconverter.cpp

include $(BUILD_SHARED_LIBRARY)