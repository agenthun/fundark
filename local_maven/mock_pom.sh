#!/usr/bin/env bash
script_path=$(dirname $(readlink -f $0))

replaceResStrValue()
{
    key="$1"
    value="$2"
    file="$3"
    value=`echo ${value} | sed 's:\/:\\\/:g'`
    value=`echo ${value} | sed 's:\.:\\\.:g'`
#    echo "replaceResStrValue, key = ${key}, value = ${value}"
    sed -i "" "s/\(<${key}>\)[^<\/]*/\1${value}/g" ${file}
}
file=${script_path}/test.pom

aars=(
      "androidpermissionmanager"
      "arcore_client"
      "arpresto"
      "huawei_ar_engine_plugin_required"
      "huawei_ar_engine_unityplugin"
      "nativegallery"
      "nativeshare"
      "runtimepermissions"
      "standardar"
      "unityandroidpermissions"
      "unityarcore"
      "unitylibrary_release"
)

for aar in "${aars[@]}"; do
  replaceResStrValue "artifactId" "${aar}" "${file}"
  cp -rf ${file} ${script_path}/com/local/${aar}/1.0.0/${aar}-1.0.0.pom
  echo "implementation(\"com.local:${aar}:1.0.0\")"
done

