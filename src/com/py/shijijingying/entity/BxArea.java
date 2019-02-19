package com.py.shijijingying.entity;

public class BxArea {
    private Integer id;

    private String name;

    private Double priceOne;

    private Double bxadd;

    private String postage;

    private Double more;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPriceOne() {
        return priceOne;
    }

    public void setPriceOne(Double priceOne) {
        this.priceOne = priceOne;
    }

 
    public Double getBxadd() {
		return bxadd;
	}

	public void setBxadd(Double bxadd) {
		this.bxadd = bxadd;
	}

	public String getPostage() {
        return postage;
    }

    public void setPostage(String postage) {
        this.postage = postage;
    }

    public Double getMore() {
        return more;
    }

    public void setMore(Double more) {
        this.more = more;
    }
}