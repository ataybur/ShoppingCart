package com.ataybur.cart.model;

public class Category {
	private String title;
	private Category parentCategory;

	public Category(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parentCategory == null) ? 0 : parentCategory.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (parentCategory == null) {
			if (other.parentCategory != null)
				return false;
		} else if (!parentCategory.equals(other.parentCategory))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
