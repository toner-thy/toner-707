package com.cdthgk.platform.dataAuthority.vo;
import java.io.Serializable;
public class DataAuthority implements Serializable {
        private static final long serialVersionUID = 5431999106578705886L;

        private String dataId;

	private String name;

	private int dataType;
	private int sort;

	private DataAuthority dataAuthority;
        public int getSort() {
                return sort;
        }

        public void setSort(int sort) {
                this.sort = sort;
        }

        private String describe;
	public String getDataId() {
                return dataId;
        }

        public void setDataId(String dataId) {
                this.dataId = dataId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getDataType() {
                return dataType;
        }

        public void setDataType(int dataType) {
                this.dataType = dataType;
        }

        public String getDescribe() {
                return describe;
        }

        public void setDescribe(String describe) {
                this.describe = describe;
        }

        public DataAuthority getDataAuthority() {
                return dataAuthority;
        }

        public void setDataAuthority(DataAuthority dataAuthority) {
                this.dataAuthority = dataAuthority;
        }

}
