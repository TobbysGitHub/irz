/**
 * 将json对象的字段置空
 * 
 * @param srcParas
 *            需要重置的json对象
 * @param constantParas
 *            重置对象中保持不变的变量，数组类型
 */
function reset(srcParas, constantParas) {
	for ( var key in srcParas) {
		if (constantParas.indexOf(key) < 0) {
			if (typeof srcParas[key] == 'function') {
				srcParas[key]("");
			}
		}
	}
	return srcParas;
}