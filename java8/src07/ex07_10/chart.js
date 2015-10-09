function readData(path) {
	var lines = java.nio.file.Files.readAllLines(new java.io.File(path).toPath())
	var map = new java.util.HashMap();
	
	for each (var line in lines) {
		var words = line.split(" ")
		map.put(words[0], words[1]);
	}
	
	return map;
}

function createChart(data) {
	var entryToData = function (entry) {
		return new javafx.scene.chart.PieChart.Data(entry.getKey(), entry.getValue());
	}
	var compare = function (e1, e2) {
		return e2.getKey().compareTo(e1.getKey())
	}
	
	var dataSet = data.entrySet().stream().sorted(compare).map(entryToData).collect(java.util.stream.Collectors.toList())
	var list = javafx.collections.FXCollections.observableArrayList(dataSet)
	
	return new javafx.scene.chart.PieChart(list)
}

var data = readData("data.txt")
var chart = createChart(data)
$STAGE.scene = new javafx.scene.Scene(chart)
$STAGE.title = "Chart"
