#!/bin/bash
mvn test -DfailIfNoTests=false -Dbrowser=chrome -Denvironment=dev -Dtest=TestRunner
