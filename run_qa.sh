#!/bin/bash
mvn test -DfailIfNoTests=false -Dbrowser=firefox -Denvironment=qa -Dtest=TestRunner
