#!/bin/sh

export JAVA_HOME=/usr/lib/jvm/jdk1.7.0_51

echo "BrowserStackLocal instances:"
tasklist BrowserStackLocal

if tasklist BrowserStackLocal; then
  echo "BrowserStackLocal running already"
else
  wget https://www.browserstack.com/browserstack-local/BrowserStackLocal-win32.zip
  unzip BrowserStackLocal-win32.zip
  BrowserStackLocal.exe  h5QPWAsqT6qfxrJjrtqZ &
fi
