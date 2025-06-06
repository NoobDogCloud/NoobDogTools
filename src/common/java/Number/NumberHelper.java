package common.java.Number;

import common.java.String.StringHelper;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberHelper {
	public static long number2long(Object in) {
		long r = 0;
		if (in == null) {
			return r;
		}
		try {
			if (in instanceof Long) {
				r = (Long) in;
			} else if (in instanceof Integer) {
				r = ((Integer) in).longValue();
			} else if (in instanceof String) {
				r = Long.parseLong(((String) in).trim());
			} else if (in instanceof Double) {
				r = ((Double) in).longValue();
			} else if (in instanceof Float) {
				r = ((Float) in).longValue();
			} else if (in instanceof Boolean) {
				r = (Boolean) in ? 0 : 1;
			} else if (in instanceof BigInteger) {
				r = ((BigInteger) in).longValue();
			} else if (in instanceof BigDecimal) {
				r = ((BigDecimal) in).longValue();
			}
		} catch (Exception ignored) {
		}
		return r;
	}

	public static int number2int(Object in) {
		int r = 0;
		if (in == null) {
			return r;
		}
		try {
			if (in instanceof Long) {
				r = ((Long) in).intValue();
			} else if (in instanceof Integer) {
				r = (Integer) in;
			} else if (in instanceof String) {
				r = Integer.parseInt(((String) in).trim());
			} else if (in instanceof Double) {
				r = ((Double) in).intValue();
			} else if (in instanceof Float) {
				r = ((Float) in).intValue();
			} else if (in instanceof Boolean) {
				r = (Boolean) in ? 0 : 1;
			} else if (in instanceof BigInteger) {
				r = ((BigInteger) in).intValue();
			} else if (in instanceof BigDecimal) {
				r = ((BigDecimal) in).intValue();
			}
		} catch (Exception ignored) {
		}
		return r;
	}

	public static float number2float(Object in) {
		float r = 0.f;
		if (in == null) {
			return r;
		}
		try {
			if (in instanceof Long) {
				r = ((Long) in).floatValue();
			} else if (in instanceof Integer) {
				r = ((Integer) in).floatValue();
			} else if (in instanceof String) {
				r = Float.parseFloat(((String) in).trim());
			} else if (in instanceof Double) {
				r = ((Double) in).floatValue();
			} else if (in instanceof Float) {
				r = (Float) in;
			} else if (in instanceof Boolean) {
				r = (Boolean) in ? 0.0f : 1.0f;
			} else if (in instanceof BigInteger) {
				r = ((BigInteger) in).floatValue();
			} else if (in instanceof BigDecimal) {
				r = ((BigDecimal) in).floatValue();
			}
		} catch (Exception e) {
			r = 0.f;
		}
		return r;
	}

	public static double number2double(Object in) {
		double r = 0.d;
		if (in == null) {
			return r;
		}
		try {
			if (in instanceof Long) {
				r = ((Long) in).doubleValue();
			} else if (in instanceof Integer) {
				r = ((Integer) in).doubleValue();
			} else if (in instanceof String) {
				r = Double.parseDouble(((String) in).trim());
			} else if (in instanceof Double) {
				r = ((Double) in);
			} else if (in instanceof Float) {
				r = ((Float) in).doubleValue();
			} else if (in instanceof Boolean) {
				r = (Boolean) in ? 0.0d : 1.0d;
			} else if (in instanceof BigInteger) {
				r = ((BigInteger) in).doubleValue();
			} else if (in instanceof BigDecimal) {
				r = ((BigDecimal) in).doubleValue();
			}
		} catch (Exception e) {
			r = 0.d;
		}
		return r;
	}

	public static boolean isNumeric(Object str) {
		var v = StringHelper.toString(str);
		if (v == null || v.isEmpty()) {
			return false;
		}
		for (int i = v.length(); --i >= 0; ) {
			if (!Character.isDigit(v.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
