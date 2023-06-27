package com.student.model;

import com.student.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BranchObjectResponse {
   private boolean result;
   private String message;

   private Branch data;

}
