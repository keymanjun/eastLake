package com.framework.components.pager;
import java.util.List;
/**
 * ·ÖÒ³Àà
 * @author dafei
 *
 */
public class  PaginationSupport 
{
	 public final static int PAGESIZE = 15;

	 private int pageSize = PAGESIZE;
	 
	 private int totalCount;
	 private int currentPage;
	 private int pageCount;
	 private int[] indexes = new int[0];
	 private int startIndex;
	
	 private List items;
	 
	 public PaginationSupport(List items, int totalCount) {
	  setPageSize(PAGESIZE);
	  setTotalCount(totalCount);
	  setItems(items);
	  setStartIndex(0);
	  setPageCount(totalCount);
	  setCurrentPage(currentPage);
	 }

	 public  PaginationSupport(List items, int totalCount, int startIndex) {
	  setPageSize(PAGESIZE);
	  setTotalCount(totalCount);
	  setItems(items);
	  setStartIndex(startIndex);
	  setPageCount(totalCount);
	  setCurrentPage(currentPage);
	 }

	 public  PaginationSupport(List items, int totalCount, int pageSize,int startIndex) {
	  setPageSize(pageSize);
	  setTotalCount(totalCount);
	  setItems(items);
	  setStartIndex(startIndex);
	  setPageCount(totalCount);
	  setCurrentPage(currentPage);
	 }

	 
	 public void setTotalCount(int totalCount) {
	  if (totalCount > 0) {
	   this.totalCount = totalCount;
	   int count = totalCount / pageSize;
	   if (totalCount % pageSize > 0)
	    count++;
	   indexes = new int[count];
	   for (int i = 0; i < count; i++) {
	    indexes[i] = pageSize * i;
	   }
	    } else {
	   this.totalCount = 0;
	  }
	 }
	 public int getTotalCount() {
	  return totalCount;
	 }
	 public void setIndexes(int[] indexes) {
	  this.indexes = indexes;
	 }
	 public int[] getIndexes() {
	  return indexes;
	 }

	 
	 public void setStartIndex(int startIndex) {
	  if (totalCount == 0)
	   this.startIndex = 0;
	  else if (startIndex >= totalCount)
	   this.startIndex = indexes[indexes.length - 1];	  
	  else {
	   this.startIndex = indexes[startIndex / pageSize];
	  }
	 }
	 public int getStartIndex() {
	  return startIndex;
	 }

	 
	 public void setPageCount(int totalCount) {
	  int count = totalCount / pageSize;
	  if (totalCount % pageSize > 0)
	   count++;
	  this.pageCount=count;
	 }
	 public int getPageCount() {
	  return pageCount;
	 }
	 

	 public int getCurrentPage() {
		 return currentPage;
	 }

	 public void setCurrentPage(int currentPage) {
		 this.currentPage= getStartIndex() / pageSize + 1;
	  
	 }

	 public int getPageSize() {
	  return pageSize;
	 }

	 public void setPageSize(int pageSize) {
	  this.pageSize = pageSize;
	 }


	 public List getItems() {
	  return items;
	 }

	 public void setItems(List items) {
	  this.items = items;
	 }


	}


