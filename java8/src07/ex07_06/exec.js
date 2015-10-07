function pipe() {
	output = ''
	
	for each(var arg in arguments) {
		var output = $EXEC(arg, output)
	}
}
