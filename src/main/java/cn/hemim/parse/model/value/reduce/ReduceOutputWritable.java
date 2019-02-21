package cn.hemim.parse.model.value.reduce;

import cn.hemim.common.KpiType;
import cn.hemim.parse.model.value.BaseOutputWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.WritableUtils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class ReduceOutputWritable extends BaseOutputWritable {
    // 标识
    private KpiType kpiType;
    private MapWritable valueMap;

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        WritableUtils.writeEnum(dataOutput, kpiType);
        valueMap.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        kpiType = WritableUtils.readEnum(dataInput, KpiType.class);
        valueMap.readFields(dataInput);
    }

    public KpiType getKpiType() {
        return kpiType;
    }

    public void setKpiType(KpiType kpiType) {
        this.kpiType = kpiType;
    }

    public MapWritable getValueMap() {
        return valueMap;
    }

    public void setValueMap(MapWritable valueMap) {
        this.valueMap = valueMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReduceOutputWritable that = (ReduceOutputWritable) o;
        return kpiType == that.kpiType &&
                Objects.equals(valueMap, that.valueMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kpiType, valueMap);
    }
}
