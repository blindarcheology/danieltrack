#!/usr/bin/env bash
# shellcheck disable=SC2006
time=`date -u -d"+8 hour" + '%Y-%m-%d %H:%M:%S'`
cpu_warn='75'
memory_warn='100'
disk_warn='80'

item_cpu(){
  # shellcheck disable=SC2006
  cpu_idle=`top -b -n 1 | grep Cpu | awk '{print $8}' | cut -f 1 -d "."`
  # shellcheck disable=SC2154
  cpu_use=`expr 100 - "$cpu_cpu_idle"`
  if [ "$cpu_use" -gt $cpu_warn ]
    then
       echo "cpu warining"
    else
      echo "cpu is ok"
  fi
}

item_cpu