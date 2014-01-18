println "[Try Groovy]"

println "\n[Basic]"
println "Hello, World!"
println "Wan!"
for (def i = 0; i < 3; i++) {
	println "$i: Hello!"}println "\n[List]"
def ls = [1, 2, 3]for (int element : ls) {
	println element
}// Closure
def str = "Hello World"
def readerClosure = { println str }
readerClosure()
def writerClosure = { str = "foo" }
writerClosure()
println str

// Read File
println " "
println "/* * * * * * * * * * * * * * * * * * * * * * * * "
new File("./src/main/groovy/HelloTest.groovy").eachLine { println it }
println "* * * * * * * * * */"
println " "
