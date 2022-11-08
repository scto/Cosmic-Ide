// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.codeInsight;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.Collection;

public abstract class FileModificationService2 {
  public static FileModificationService2 getInstance() {
    return ApplicationManager.getApplication().getService(FileModificationService2.class);
  }

  public abstract boolean preparePsiElementsForWrite(@NonNull Collection<? extends PsiElement> elements);
  public abstract boolean prepareFileForWrite(@Nullable final PsiFile psiFile);

  public boolean preparePsiElementForWrite(@Nullable PsiElement element) {
    PsiFile file = element == null ? null : element.getContainingFile();
    return prepareFileForWrite(file);
  }

  public boolean preparePsiElementsForWrite(@NonNull PsiElement... elements) {
    return preparePsiElementsForWrite(Arrays.asList(elements));
  }

  public abstract boolean prepareVirtualFilesForWrite(@NonNull Project project, @NonNull Collection<? extends VirtualFile> files);
}
