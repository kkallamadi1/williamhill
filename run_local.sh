#!/bin/bash
mvn test -DfailIfNoTests=false -Dbrowser=chrome -Denvironment=qa -Dtest=TestRunner
