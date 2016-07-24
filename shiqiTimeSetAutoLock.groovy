/**
 *  TimeSetAutoLock
 *
 *  Copyright 2016 Yunhan Jia & Shiqi Wang
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "TimeSetAutoLock",
    namespace: "wsq",
    author: "Yunhan Jia & Shiqi Wang",
    description: "autolock attack",
    category: "Safety & Security",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    oauth: true)


preferences {
	section("Title") {
		input "mytime", "time"
        input "mylock", "capability.lock"
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"
	
	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	// TODO: subscribe to attributes, devices, locations, etc.
    log.debug "mytime: $mytime"
    //Date attackertime = new Date("16:08")
    log.debug "$mytime"
    schedule(mytime, timeLockHandler)
    //schedule(attackertime, attackerTimeLockHandler)
}

def timeLockHandler(evt) {
	mylock.lock()
}

def attackerTimeLockHandler(evt) {
	mylock.unlock()
}