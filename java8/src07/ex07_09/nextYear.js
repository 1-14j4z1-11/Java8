var length = arguments.length
var age = -1

if(length >= 1) {
	age = Number(arguments[0])
}
else if($ENV.AGE != undefined) {
	var age = Number($ENV.AGE)
}
else {
	var line = readLine('Age : ')
	var age = Number(line)
}

age = age + 1

print('Next year, you will be ' + age);