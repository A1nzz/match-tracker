import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Hero } from '../../../models/models';

@Component({
  selector: 'app-hero-form',
  imports: [ReactiveFormsModule],
  templateUrl: './hero-form.component.html',
  styleUrls: ['./hero-form.component.scss'],
})
export class HeroFormComponent {
  heroForm: FormGroup;
  isEditMode: boolean = false;

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<HeroFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Hero | null
  ) {
    this.heroForm = this.fb.group({
      name: ['', Validators.required],
      logoUrl: ['', Validators.required],
      attackType: ['', Validators.required],
      primaryAttribute: ['', Validators.required],
      roles: ['', Validators.required],
    });

    if (data) {
      this.isEditMode = true;
      this.heroForm.patchValue(data);
    }
  }

  saveHero(): void {
    if (this.heroForm.valid) {
      this.dialogRef.close(this.heroForm.value);
    }
  }

  close(): void {
    this.dialogRef.close();
  }
}