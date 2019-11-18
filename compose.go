package main

import (
	"flag"
	"os"
	"os/exec"
	"strings"
	"syscall"
)

func main() {
	all := flag.Bool("a", false, "package&build&restart all")
	packModules := flag.String("-package", "service/user,web-service", "part package")
	mvn, lookErr := exec.LookPath("mvn")
	if lookErr != nil {
		panic(lookErr)
	}
	env := os.Environ()

	compose, lookErr := exec.LookPath("docker-compose")
	if lookErr != nil {
		panic(lookErr)
	}

	services := flag.Args()[0]
	if *all {
		execErr := syscall.Exec(mvn, []string{"-T 1C", "clean", "package"}, env)
		if execErr != nil {
			panic(execErr)
		}

		execErr = syscall.Exec(compose, []string{"-H tcp://xxx", "--build", "up"}, env)
		if execErr != nil {
			panic(execErr)
		}
	} else {
		execErr := syscall.Exec(mvn, []string{"-T 1C", "", strings.Join([]string{"clean package -pl", *packModules, "-am"}, " ")}, env)
		if execErr != nil {
			panic(execErr)
		}
		execErr = syscall.Exec(compose, []string{"-H tcp://xxx", "--build", "up", services}, env)
		if execErr != nil {
			panic(execErr)
		}
	}

}
