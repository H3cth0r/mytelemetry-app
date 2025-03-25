{ pkgs ? import <nixpkgs> {
    config = {
        android_sdk.accept_license = true;
        allowUnfree = true;
    };
} }:

let
  android = pkgs.androidenv.composeAndroidPackages {
    platformVersions = [ "34" ];
    buildToolsVersions = [ "34.0.0" ];
    abiVersions = [ "armeabi-v7a" "arm64-v8a" ];
    includeEmulator = false;
    includeSystemImages = false;
    useGoogleAPIs = false;
  };
in
pkgs.mkShell {
  packages = [
    android.androidsdk
    pkgs.jdk17
    pkgs.gradle
    pkgs.zlib
    pkgs.autoPatchelfHook
    pkgs.stdenv.cc.cc.lib
  ];

  # Fix for AAPT2 binary
  shellHook = ''
    export LD_LIBRARY_PATH=${pkgs.stdenv.cc.cc.lib}/lib:${pkgs.zlib}/lib
    autoPatchelf $HOME/.gradle/caches/
  '';

  ANDROID_SDK_ROOT = "${android.androidsdk}/libexec/android-sdk";
  ANDROID_HOME = "${android.androidsdk}/libexec/android-sdk";
}
