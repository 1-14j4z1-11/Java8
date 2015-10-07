function factory() {
	var arr = new (Java.extend(java.util.ArrayList)) {
		add : function(x) {
			print('Adding : ' + x);
			return Java.super(arr).add(x);
		}
	}
	return arr;
}

var arr1 = factory()
arr1.add('item1')

var arr2 = factory()
arr2.add('item2')